package com.stayrascal.cloud.bff.staff;

import com.stayrascal.cloud.address.contract.client.AddressServiceClient;
import com.stayrascal.cloud.address.contract.dto.AddressDto;
import com.stayrascal.cloud.bff.staff.request.CreateClerkCommand;
import com.stayrascal.cloud.bff.staff.response.ClerkResponse;
import com.stayrascal.cloud.common.contract.QueryMap;
import com.stayrascal.cloud.common.contract.auth.Authorization;
import com.stayrascal.cloud.common.contract.auth.IdentityType;
import com.stayrascal.cloud.common.contract.result.CreatedResult;
import com.stayrascal.cloud.common.contract.result.PageResult;
import com.stayrascal.cloud.common.mapper.DefaultMapper;
import com.stayrascal.cloud.mapping.contract.client.MappingServiceClient;
import com.stayrascal.cloud.user.admin.contract.client.StaffServiceClient;
import com.stayrascal.cloud.user.admin.contract.command.CreateStaffCommand;
import com.stayrascal.cloud.user.admin.contract.dto.StaffDto;
import com.stayrascal.cloud.user.auth.contract.AuthenticationType;
import com.stayrascal.cloud.user.auth.contract.client.AuthServiceClient;
import com.stayrascal.cloud.user.auth.contract.command.CreateAuthenticationCommand;
import com.stayrascal.cloud.user.auth.contract.dto.AuthenticationDto;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;
import org.joda.time.DateTime;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class StaffServiceWithRxJava {
    public static final String CLERK_ROLE_ID = "store-click-role-id";
    private final AuthServiceClient authClient;
    private final StaffServiceClient staffClient;
    private final AddressServiceClient addressClient;
    private final MappingServiceClient mappingClient;
    private final DefaultMapper mapper;

    @Autowired
    public StaffServiceWithRxJava(AuthServiceClient authClient, StaffServiceClient staffClient,
                                  AddressServiceClient addressClient, MappingServiceClient mappingClient) {
        this.authClient = authClient;
        this.staffClient = staffClient;
        this.addressClient = addressClient;
        this.mappingClient = mappingClient;
        this.mapper = DefaultMapper.builder().build();
    }


    public PageResult listClerk(String storeId, Integer pageSize, Integer pageIndex) {
        final PageResult result = staffClient.listStaffs(pageSize, pageIndex, QueryMap.builder().put("store_id", storeId).build());
        final List<StaffDto> items = result.getItems();
        result.setItems(items.stream().map(this::mapToClerkResponse).collect(Collectors.toList()));
        return result;
    }

    public PageResult listClerk(Integer pageSize, Integer pageIndex) {
        final PageResult result = staffClient.listStaffs(pageSize, pageIndex, new HashMap<>());
        final List<StaffDto> items = result.getItems();
        result.setItems(items.stream().map(this::mapToClerkResponse).collect(Collectors.toList()));
        return result;
    }

    public PageResult listClerks(Integer pageSize, Integer pageIndex) {
        final PageResult pageResult = new PageResult(0L, pageSize, pageIndex, new ArrayList<ClerkResponse>());

        Flowable.create((FlowableOnSubscribe<StaffDto>) emitter -> {
            PageResult result = staffClient.listStaffs(pageSize, pageIndex, new HashMap<>());
            pageResult.setTotalCount(result.getTotalCount());
            final List<StaffDto> items = result.getItems();
            items.stream().forEach(item -> emitter.onNext(item));
        }, BackpressureStrategy.BUFFER).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(new Subscriber<StaffDto>() {
                    Subscription mSubscription = null;

                    @Override
                    public void onSubscribe(Subscription s) {
                        mSubscription = s;
                        s.request(1L);
                    }

                    @Override
                    public void onNext(StaffDto staffDto) {
                        pageResult.getItems().add(mapToClerkResponse(staffDto));
                        mSubscription.request(1L);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return pageResult;

        /*final PageResult result = staffClient.listStaffs(pageSize, pageIndex, new HashMap<>());
        final List<StaffDto> items = result.getItems();
        result.setItems(items.stream().map(this::mapToClerkResponse).collect(Collectors.toList()));
        return result;*/
    }

    public CreatedResult createClerk(String storeId, CreateClerkCommand command) {
        final CreatedResult result = staffClient.createStaff(mapToCreateStaffCommand(storeId, command.getName(), command.getContact()));
        authClient.createAuthentication(generateCreateAuthenticationCommand(result.getId(), command.getPassword()));
        return result;

    }

    public ClerkResponse getClerk(String clerkId) {
        return mapToClerkResponse(staffClient.getStaff(clerkId));
    }

    private ClerkResponse mapToClerkResponse(StaffDto staffDto) {
        final ClerkResponse response = mapper.map(staffDto, ClerkResponse.class);

        Observable<List<AuthenticationDto>> authenticationDtoObservable =
                Observable.create((ObservableOnSubscribe<List<AuthenticationDto>>) authenticationEmitter -> {
                    List items = authClient.listAuthentications(1, 0, QueryMap.builder()
                            .put("identity_type", IdentityType.STAFF.name())
                            .put("identity_id", staffDto.getId()).build()).getItems();
                    authenticationEmitter.onNext(items);
                    authenticationEmitter.onComplete();
                }).subscribeOn(Schedulers.io());

        Observable<AddressDto> addressIdsObservable = Observable.create((ObservableOnSubscribe<Long>) emitter -> {
            List<Long> addressIds = mappingClient.retrieveAddressIds(staffDto.getId()).getItems();
            emitter.onNext(addressIds.get(0));
            emitter.onComplete();
        }).map(addressId -> addressClient.get(addressId)).subscribeOn(Schedulers.io());

        return Observable.zip(authenticationDtoObservable, addressIdsObservable, (authenticationDtos, addressDto) -> {
            response.setLoginId(authenticationDtos.stream()
                    .filter(item -> item.getAuthenticationType() == AuthenticationType.PASSWORD)
                    .findFirst()
                    .get().getAuthenticationName());
            response.setAddressName(addressDto.getName());
            return response;
        }).blockingFirst();

        /*Observable<Object> authenticationDtoObservable = Observable.create(subscriber -> {
            subscriber.onNext(
                    authClient.listAuthentications(1, 0, QueryMap.builder()
                            .put("identity_type", IdentityType.STAFF.name())
                            .put("identity_id", staffDto.getId()).build()).getItems()
            );
            subscriber.onCompleted();
        }).subscribeOn(Schedulers.io());

        Observable<AddressDto> addressIdsObservable = Observable.create((Observable.OnSubscribe<Long>) subscriber -> {
            List<Long> addressIds = mappingClient.retrieveAddressIds(staffDto.getId()).getItems();
            subscriber.onNext(addressIds.get(0));
            subscriber.onCompleted();
        }).map(mapping -> addressClient.get(mapping)).subscribeOn(Schedulers.io());

        return Observable.zip(authenticationDtoObservable, addressIdsObservable, (authenticationDtosObj, addressDto) -> {
            List<AuthenticationDto> authenticationDtos = (List<AuthenticationDto>) authenticationDtosObj;
            response.setLoginId(authenticationDtos.stream()
                    .filter(item -> item.getAuthenticationType() == AuthenticationType.PASSWORD)
                    .findFirst()
                    .get().getAuthenticationName());
            response.setAddressName(addressDto.getName());
            return response;
        }).blockingFirst();*/
    }

    private CreateAuthenticationCommand generateCreateAuthenticationCommand(String staffId, String password) {
        CreateAuthenticationCommand command = new CreateAuthenticationCommand();
        command.setIdentityId(staffId);
        command.setIdentityType(IdentityType.STAFF);
        command.setAuthenticationName(generateLoginId());
        command.setAuthenticationType(AuthenticationType.PASSWORD);
        command.setPrimaryKeyValue(password);
        command.setPrimaryKeyExpireTime(DateTime.now().plusYears(100).toDate());
        return command;
    }

    private String generateLoginId() {
        final PageResult pageResult = staffClient.listStaffs(1, 0, Maps.newHashMap());
        return String.format("%05d", pageResult.getTotalCount().intValue());
    }

    private CreateStaffCommand mapToCreateStaffCommand(String storeId, String name, String contact) {
        CreateStaffCommand command = new CreateStaffCommand();
        command.setMobile(contact);
        command.setName(name);
        command.setAuthorizations(Lists.newArrayList(new Authorization(storeId, CLERK_ROLE_ID)));
        return command;
    }
}

