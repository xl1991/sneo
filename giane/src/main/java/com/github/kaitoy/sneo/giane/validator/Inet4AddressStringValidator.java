/*_##########################################################################
  _##
  _##  Copyright (C) 2013 Kaito Yamada
  _##
  _##########################################################################
*/

package com.github.kaitoy.sneo.giane.validator;

import java.net.InetAddress;
import java.util.regex.Pattern;
import com.opensymphony.xwork2.validator.ValidationException;
import com.opensymphony.xwork2.validator.validators.FieldValidatorSupport;

public class Inet4AddressStringValidator extends FieldValidatorSupport {

  private static final Pattern INET4_ADDRESS_PATTERN = Pattern.compile("[0-9]{1,3}(\\.[0-9]{1,3}){3}");

  public void validate(Object object) throws ValidationException {
    String validatingFieldName = getFieldName();
    String inetAddrStr = (String)getFieldValue(validatingFieldName, object);
    if (!isValid(inetAddrStr)) {
      addFieldError(validatingFieldName, object);
    }
  }

  public static boolean isValid(String inetAddrStr) {
    if (!INET4_ADDRESS_PATTERN.matcher(inetAddrStr).matches()) {
      return false;
    }

    try {
      InetAddress.getByName(inetAddrStr);
    } catch (Exception e) {
      return false;
    }

    return true;
  }

}
