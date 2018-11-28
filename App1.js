import React, { Component } from 'react';
import {
    StyleSheet,
    Text,
    View,
    Image,
    Button
} from 'react-native';
import {AppStackNavigator} from './AppStackNavigator';
import Global from './Global';

export default class App1 extends Component {
    /**
     * 视图树挂载前调用
     * 获取到native返回回来的信息保存到全局变量中
     */
    componentWillMount() {
        Global.pageIndex=this.props.pageIndex;
    }
    render(){
        return (
            <AppStackNavigator/>
        )
    }
}