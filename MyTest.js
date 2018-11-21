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

export default class MyTest extends Component {
    componentWillMount() {
        console.log("----componentWillMount"+this.props.flag);
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

