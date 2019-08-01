package com.future.tcfm.repository;

import com.future.tcfm.model.Expense;
import com.future.tcfm.service.ExpenseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends MongoRepository<Expense, String>   {
    Expense findByTitle(String title);
    List<Expense> findByGroupNameLikeAndStatusOrderByCreatedDateDesc(String groupName,Boolean bool);
    List<Expense> findByGroupNameLikeOrderByCreatedDateDesc(String groupName);
    Page<Expense> findByGroupNameOrderByCreatedDateDesc(String groupName, Pageable pageable);
    Page<Expense> findAllByTitleContainsIgnoreCaseAndStatusContainsIgnoreCase(String title,Boolean status,Pageable pageable);
    Page<Expense> findByTitleContainsIgnoreCaseOrderByCreatedDateDesc(String title,Pageable pageable);
    Page<Expense> findByStatusOrderByCreatedDateDesc(Boolean status,Pageable pageable);
    Page<Expense> findByPriceLessThanEqualOrderByCreatedDate(double price,Pageable pageable);
    Page<Expense> findByPriceGreaterThanEqualOrderByCreatedDate(double price,Pageable pageable);

    Page<Expense> findByCreatedDateLessThanEqualOrderByStatus(long dateInMillis,Pageable pageable);


    @Query("{'?0' : { $regex: '?1', $options: 'i' } }")
    Page<Expense> findAll(
            String field,
            String value,
            Pageable pageable);

    Expense findByIdExpense(String id);
    Expense findTopByGroupNameAndStatusOrderByLastModifiedAtDesc(String gName,Boolean bool);
    List<Expense> findTop10ByGroupNameOrderByCreatedDateDesc(String groupName);

}
