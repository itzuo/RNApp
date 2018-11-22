import React, { Component } from 'react';
import {
    AppRegistry,
    StyleSheet,
    Text,
    View,
    Button
} from 'react-native';

import ToastExample from './ToastExample';

// import { NativeModules } from "react-native";
import CockView from './CockView';
export default class MyTest extends Component {
    componentWillMount() {
        console.log("----componentWillMount"+this.props.flag);
    }
    componentDidMount() {
        console.log("MyTest--componentDidMount")
        setInterval(()=>{
            console.log("MyTest--wwww111122")
        },1000);
    }
    render() {
        return (
            <View style={styles.container}>
                <Text style={styles.welcome}>
                    RN的界面
                </Text>
                <Text style={{fontSize:30,marginBottom:20,marginRight:20}}>
                    RN的界面的Text
                </Text>
                <View style={{marginBottom:20}}>
                   <CockView/>
                <Button
                    title='Toast'
                    onPress={()=>{
                        ToastExample.show("Awesome", ToastExample.SHORT);
                    }}/>
                </View>
            </View>
        );
    }
}

const styles = StyleSheet.create({
    container: {
        justifyContent: 'center',
        alignItems: 'center',
        backgroundColor: 'red',
    },
    welcome: {
        fontSize: 20,
        textAlign: 'center',
        margin: 10,
    }
});

