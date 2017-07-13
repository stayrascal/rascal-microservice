package com.stayrascal.cloud.common.lib.jpa;

import com.exmertec.yaz.BaseDao;
import com.exmertec.yaz.core.Query;
import com.stayrascal.cloud.common.lib.constant.ErrorCode;
import com.stayrascal.cloud.common.lib.jersey.exception.BadRequestException;
import com.stayrascal.cloud.common.query.SortQuery;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class QueryHelper {
    public QueryHelper() {
    }

    public static <T> List<T> list(BaseDao<T> dao, SortQuery sortQuery, Collection<Query> filters) {
//        switch (null.$SwitchMap$com$stayrascal$cloud$common$enumeration$SortType[sortQuery.getSortType().ordinal()]) {
        switch (sortQuery.getSortType().ordinal()) {
            case 1:
                return dao.where(filters).descendingBy(new String[]{sortQuery.getSortBy()}).queryPage(sortQuery.getPageSize().intValue(), sortQuery.getPageIndex().intValue());
            case 2:
                return dao.where(filters).ascendingBy(new String[]{sortQuery.getSortBy()}).queryPage(sortQuery.getPageSize().intValue(), sortQuery.getPageIndex().intValue());
            default:
                throw new BadRequestException(ErrorCode.INTERNAL_ERROR, "Sorting type not supported {}", new Object[]{sortQuery.getSortType()});
        }
    }

    public static <T> List<T> list(BaseDao<T> dao, SortQuery sortQuery, Query... filters) {
        return list(dao, sortQuery, (Collection) Arrays.asList(filters));
    }
}
