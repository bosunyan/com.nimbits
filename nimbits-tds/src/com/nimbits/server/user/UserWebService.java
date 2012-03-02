/*
 * Copyright (c) 2010 Tonic Solutions LLC.
 *
 * http://www.nimbits.com
 *
 *
 * Licensed under the GNU GENERAL PUBLIC LICENSE, Version 3.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.gnu.org/licenses/gpl.html
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the license is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, eitherexpress or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.nimbits.server.user;

import com.nimbits.client.model.*;
import com.nimbits.client.model.user.*;
import com.nimbits.server.gson.*;

import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class UserWebService extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
        final String result;
        final PrintWriter out;
        final String action = req.getParameter(Const.Params.PARAM_ACTION);

        if (!(action == null) && action.equals(Const.ACTION_DOWNLOAD)) {
            out = resp.getWriter();
            final List<User> users = UserTransactionFactory.getInstance().getAllUsers("dateCreated ascending", 1000);


            result = GsonFactory.getInstance().toJson(users, GsonFactory.userListType);
            out.println(result);
        }
        if (!(action == null) && action.equals(Const.ACTION_CREATE)) {

        }

    }

}
