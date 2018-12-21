import React, {Component} from 'react';
import {requireNativeComponent,View} from 'react-native';
import PropTypes from 'prop-types'

let iface = {
  name:'FocusedTextView',
  propTypes:{
      text:PropTypes.string,
      size:PropTypes.number,
      ...View.propTypes
  }
};
module.exports = requireNativeComponent("FocusedTextView",iface);

