/*
 * Copyright 2016 Benjamin Sautner
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.nimbits.server.orm;

import com.nimbits.client.model.common.impl.CommonFactory;
import com.nimbits.client.model.email.EmailAddress;
import com.nimbits.client.model.entity.Entity;
import com.nimbits.client.model.user.LoginInfo;
import com.nimbits.client.model.user.User;
import com.nimbits.client.model.user.UserModelFactory;
import com.nimbits.client.model.user.UserSource;

import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import java.util.Date;


@PersistenceCapable
public class UserEntity extends EntityStore implements User {


    @Persistent
    private String password;

    @Persistent
    private String passwordSalt;

    @Persistent
    private String source;

    @NotPersistent
    private LoginInfo loginInfo;


    @Persistent
    private Boolean isAdmin;


    @Persistent
    private String passwordResetToken;

    @Persistent
    private Date passwordResetTokenTimestamp;



    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getPasswordSalt() {
        return passwordSalt;
    }

    //all users with null source are from google.
    @Override
    public UserSource getSource() {
        return source == null ? UserSource.google : UserSource.valueOf(source);
    }


    @SuppressWarnings("unused")
    protected UserEntity() {
    }


    public UserEntity(final User entity) {
        super(entity);

       // this.id = entity.getOwner();
        update(entity);

    }



    @Override
    public EmailAddress getEmail() {
        return CommonFactory.createEmailAddress(getName().getValue());
    }


    @Override
    public void update(final Entity update) {
        super.update(update);
        final User u = (User) update;

        this.passwordSalt = u.getPasswordSalt();
        this.password = u.getPassword();
        this.source = u.getSource().name();
        this.isAdmin = u.getIsAdmin();
        this.passwordResetTokenTimestamp = u.getPasswordResetTokenTimestamp();
        this.passwordResetToken = u.getPasswordResetToken();

    }

    @Override
    public void validate(User user) {
        super.validate(user);

    }

    @Override
    public void init(Entity anEntity) {

    }

    @Override
    public boolean getIsAdmin() {
        return isAdmin == null ? false : isAdmin;
    }

    @Override
    public void setIsAdmin(final boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public void setToken(String sessionId) {

    }

    @Override
    public String getToken() {
        return null;
    }

    @Override
    public void setEmail(EmailAddress emailAddress) {

    }

    @Override
    public void setLoginInfo(LoginInfo loginInfo) {
        this.loginInfo = loginInfo;
    }

    @Override
    public LoginInfo getLoginInfo() {
        return loginInfo == null ? UserModelFactory.createNullLoginInfo(false) : loginInfo;
    }

    @Override
    public void setPasswordResetToken(String token) {
        this.passwordResetToken = token;
    }

    @Override
    public void setPasswordResetTokenTimestamp(Date date) {
        this.passwordResetTokenTimestamp = date;
    }

    @Override
    public String getPasswordResetToken() {
        return passwordResetToken;
    }

    @Override
    public Date getPasswordResetTokenTimestamp() {
        return this.passwordResetTokenTimestamp == null ? new Date(0) : new Date(this.passwordResetTokenTimestamp.getTime());
    }

    @Override
    public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

    @Override
    public void setPassword(String cryptPassword) {
        this.password = cryptPassword;
    }


}
