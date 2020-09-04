package com.eleks.internal.codechallenge.bestcommerce.common.repository;

import com.eleks.internal.codechallenge.bestcommerce.common.entity.Merchant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantRepository extends CrudRepository<Merchant, Long> {
    Merchant findByEmail(String email);
}
