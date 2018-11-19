package com.rentavehicle.repository;

import com.rentavehicle.model.PriceListItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceListItemRepository extends JpaRepository<PriceListItem, Long> {

}
