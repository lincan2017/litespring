package org.litespring.service;

import org.litespring.dao.AccountDao;
import org.litespring.dao.ItemDao;

/**
 * @author : Lin Can
 * @date : 2019/1/3 23:11
 */
public class PetStoreService {

    private ItemDao itemDao;

    private AccountDao accountDao;

    private String env;

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

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }
}
