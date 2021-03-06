package com.stayrascal.cloud.common.jpa;

import com.stayrascal.cloud.common.constant.ErrorCode;
import com.stayrascal.cloud.common.contract.query.SortQuery;
import com.stayrascal.cloud.common.jersey.exception.BadRequestException;

import com.exmertec.yaz.BaseDao;
import com.exmertec.yaz.core.Query;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class QueryHelper {
    public QueryHelper() {
    }

    public static <T> List<T> list(BaseDao<T> dao, SortQuery sortQuery, Collection<Query> filters) {
        switch (sortQuery.getSortType()) {
            case DESC:
                return dao.where(filters).descendingBy(new String[]{sortQuery.getSortBy()}).queryPage(sortQuery.getPageSize().intValue(), sortQuery.getPageIndex().intValue());
            case ASC:
                return dao.where(filters).ascendingBy(new String[]{sortQuery.getSortBy()}).queryPage(sortQuery.getPageSize().intValue(), sortQuery.getPageIndex().intValue());
            default:
                throw new BadRequestException(ErrorCode.INTERNAL_ERROR, "Sorting type not supported {}", new Object[]{sortQuery.getSortType()});
        }
    }

    public static <T> List<T> list(BaseDao<T> dao, SortQuery sortQuery, Query... filters) {
        return list(dao, sortQuery, (Collection) Arrays.asList(filters));
    }
}
