package com.shop.admin.user;

import com.shop.common.entity.Role;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@EntityScan({"com.shop.common.entity", "com.shop.admin.user"})
@Rollback(false)
class RoleRepositoryTest {
    @Autowired
    private RoleRepository repository;

    @Test
    public void testCreateFirstRole(){
        Role roleAdmin = new Role("관리자", "모든 걸 관리한다");
        Role savedRole = repository.save(roleAdmin);
        assertThat(savedRole.getId()).isGreaterThan(0);
    }
    
    @Test
    public void testCreateRestRoles(){
        Role roleSalesperson = new Role("판매자", "제품 가격, 고객, 배송, 주문, 판매 보고 관리");
        Role roleEditor = new Role("편집자", "종류, 브랜드, 제품, 기사, 메뉴 관리");
        Role roleShipper = new Role("배송자", "제품 열람, 주문 열람, 주문 상태 변경");
        Role roleAssisstant = new Role("도우미", "질의, 상품 리뷰 관리");
        repository.saveAll(List.of(roleSalesperson, roleEditor, roleShipper, roleAssisstant));
    }

}