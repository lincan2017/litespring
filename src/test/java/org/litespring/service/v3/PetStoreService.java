package org.litespring.service.v3;

import org.litespring.dao.AccountDao;
import org.litespring.dao.ItemDao;

/**
 * @author : Lin Can
 * @date : 2019/1/3 23:11
 */
public class PetStoreService {

    private ItemDao itemDao;

    private AccountDao accountDao;

    private Integer petNum;

    public PetStoreService(ItemDao itemDao, AccountDao accountDao, Integer petNum) {
        this.itemDao = itemDao;
        this.accountDao = accountDao;
        this.petNum = petNum;
    }

    public Integer getPetNum() {
        return petNum;
    }

    public void setPetNum(Integer petNum) {
        this.petNum = petNum;
    }

    public ItemDao getItemDao() {
        return itemDao;
    }

    public void setItemDao(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    public AccountDao getAccountDao() {
        return accountDao;
    }

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }
}
