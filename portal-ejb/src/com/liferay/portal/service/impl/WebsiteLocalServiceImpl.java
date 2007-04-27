/**
 * Copyright (c) 2000-2007 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.portal.service.impl;

import com.liferay.counter.model.Counter;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.WebsiteURLException;
import com.liferay.portal.model.User;
import com.liferay.portal.model.Website;
import com.liferay.portal.model.impl.ListTypeImpl;
import com.liferay.portal.service.ListTypeServiceUtil;
import com.liferay.portal.service.base.WebsiteLocalServiceBaseImpl;
import com.liferay.portal.service.persistence.UserUtil;
import com.liferay.portal.service.persistence.WebsiteUtil;
import com.liferay.util.Validator;

import java.net.MalformedURLException;
import java.net.URL;

import java.rmi.RemoteException;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * <a href="WebsiteLocalServiceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class WebsiteLocalServiceImpl extends WebsiteLocalServiceBaseImpl {

	public Website addWebsite(
			long userId, String className, String classPK, String url,
			int typeId, boolean primary)
		throws PortalException, SystemException {

		User user = UserUtil.findByPrimaryKey(userId);
		Date now = new Date();

		validate(
			0, user.getCompanyId(), className, classPK, url, typeId,
			primary);

		long websiteId = CounterLocalServiceUtil.increment(
			Counter.class.getName());

		Website website = WebsiteUtil.create(websiteId);

		website.setCompanyId(user.getCompanyId());
		website.setUserId(user.getUserId());
		website.setUserName(user.getFullName());
		website.setCreateDate(now);
		website.setModifiedDate(now);
		website.setClassName(className);
		website.setClassPK(classPK);
		website.setUrl(url);
		website.setTypeId(typeId);
		website.setPrimary(primary);

		WebsiteUtil.update(website);

		return website;
	}

	public void deleteWebsite(long websiteId)
		throws PortalException, SystemException {

		WebsiteUtil.remove(websiteId);
	}

	public void deleteWebsites(long companyId, String className, String classPK)
		throws SystemException {

		WebsiteUtil.removeByC_C_C(companyId, className, classPK);
	}

	public Website getWebsite(long websiteId)
		throws PortalException, SystemException {

		return WebsiteUtil.findByPrimaryKey(websiteId);
	}

	public List getWebsites() throws SystemException {
		return WebsiteUtil.findAll();
	}

	public List getWebsites(long companyId, String className, String classPK)
		throws SystemException {

		return WebsiteUtil.findByC_C_C(companyId, className, classPK);
	}

	public Website updateWebsite(
			long websiteId, String url, int typeId, boolean primary)
		throws PortalException, SystemException {

		validate(websiteId, 0, null, null, url, typeId, primary);

		Website website = WebsiteUtil.findByPrimaryKey(websiteId);

		website.setModifiedDate(new Date());
		website.setUrl(url);
		website.setTypeId(typeId);
		website.setPrimary(primary);

		WebsiteUtil.update(website);

		return website;
	}

	protected void validate(
			long websiteId, long companyId, String className, String classPK,
			String url, int typeId, boolean primary)
		throws PortalException, SystemException {

		if (Validator.isNull(url)) {
			throw new WebsiteURLException();
		}
		else {
			try {
				new URL(url);
			}
			catch (MalformedURLException murle) {
				throw new WebsiteURLException();
			}
		}

		if (websiteId > 0) {
			Website website = WebsiteUtil.findByPrimaryKey(websiteId);

			companyId = website.getCompanyId();
			className = website.getClassName();
			classPK = website.getClassPK();
		}

		try {
			ListTypeServiceUtil.validate(
				typeId, className + ListTypeImpl.WEBSITE);
		}
		catch (RemoteException re) {
			throw new SystemException(re);
		}

		validate(websiteId, companyId, className, classPK, primary);
	}

	protected void validate(
			long websiteId, long companyId, String className, String classPK,
			boolean primary)
		throws PortalException, SystemException {

		// Check to make sure there isn't another website with the same company
		// id, class name, and class pk that also has primary set to true

		if (primary) {
			Iterator itr = WebsiteUtil.findByC_C_C_P(
				companyId, className, classPK, primary).iterator();

			while (itr.hasNext()) {
				Website website = (Website)itr.next();

				if ((websiteId <= 0) ||
					(website.getWebsiteId() != websiteId)) {

					website.setPrimary(false);

					WebsiteUtil.update(website);
				}
			}
		}
	}

}