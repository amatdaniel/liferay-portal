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

package com.liferay.portlet.tags.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import com.liferay.portlet.tags.service.TagsPropertyServiceUtil;

import java.rmi.RemoteException;

/**
 * <a href="TagsPropertyServiceSoap.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be overwritten
 * the next time is generated.
 * </p>
 *
 * <p>
 * This class provides a SOAP utility for the <code>com.liferay.portlet.tags.service.TagsPropertyServiceUtil</code>
 * service utility. The static methods of this class calls the same methods of the
 * service utility. However, the signatures are different because it is difficult
 * for SOAP to support certain types.
 * </p>
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a <code>java.util.List</code>, that
 * is translated to an array of <code>com.liferay.portlet.tags.model.TagsPropertySoap</code>.
 * If the method in the service utility returns a <code>com.liferay.portlet.tags.model.TagsProperty</code>,
 * that is translated to a <code>com.liferay.portlet.tags.model.TagsPropertySoap</code>.
 * Methods that SOAP cannot safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform compatible.
 * SOAP allows different languages like Java, .NET, C++, PHP, and even Perl, to
 * call the generated services. One drawback of SOAP is that it is slow because
 * it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at http://localhost:8080/tunnel-web/secure/axis.
 * Set the property <code>tunnel.servlet.hosts.allowed</code> in portal.properties
 * to configure security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author Brian Wing Shun Chan
 *
 * @see com.liferay.portlet.tags.service.TagsPropertyServiceUtil
 * @see com.liferay.portlet.tags.service.http.TagsPropertyServiceHttp
 * @see com.liferay.portlet.tags.service.model.TagsPropertySoap
 *
 */
public class TagsPropertyServiceSoap {
	public static com.liferay.portlet.tags.model.TagsPropertySoap addProperty(
		long entryId, java.lang.String key, java.lang.String value)
		throws RemoteException {
		try {
			com.liferay.portlet.tags.model.TagsProperty returnValue = TagsPropertyServiceUtil.addProperty(entryId,
					key, value);

			return com.liferay.portlet.tags.model.TagsPropertySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);
			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portlet.tags.model.TagsPropertySoap addProperty(
		java.lang.String entryName, java.lang.String key, java.lang.String value)
		throws RemoteException {
		try {
			com.liferay.portlet.tags.model.TagsProperty returnValue = TagsPropertyServiceUtil.addProperty(entryName,
					key, value);

			return com.liferay.portlet.tags.model.TagsPropertySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);
			throw new RemoteException(e.getMessage());
		}
	}

	public static void deleteProperty(long propertyId)
		throws RemoteException {
		try {
			TagsPropertyServiceUtil.deleteProperty(propertyId);
		}
		catch (Exception e) {
			_log.error(e, e);
			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portlet.tags.model.TagsPropertySoap[] getProperties(
		long entryId) throws RemoteException {
		try {
			java.util.List returnValue = TagsPropertyServiceUtil.getProperties(entryId);

			return com.liferay.portlet.tags.model.TagsPropertySoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);
			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portlet.tags.model.TagsPropertySoap[] getPropertyValues(
		long companyId, java.lang.String key) throws RemoteException {
		try {
			java.util.List returnValue = TagsPropertyServiceUtil.getPropertyValues(companyId,
					key);

			return com.liferay.portlet.tags.model.TagsPropertySoap.toSoapModels(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);
			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portlet.tags.model.TagsPropertySoap updateProperty(
		long propertyId, java.lang.String key, java.lang.String value)
		throws RemoteException {
		try {
			com.liferay.portlet.tags.model.TagsProperty returnValue = TagsPropertyServiceUtil.updateProperty(propertyId,
					key, value);

			return com.liferay.portlet.tags.model.TagsPropertySoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);
			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(TagsPropertyServiceSoap.class);
}