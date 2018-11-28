import React, {Component} from 'react';
import {Platform, StyleSheet, Text, View,Button,Alert} from 'react-native';

export default class Page1 extends Component {
    render() {
        //从props里面取得navigation
        const {navigation} =this.props;

        return (
            <View style={styles.container}>
                <Text style={styles.welcome}>Welcome to Page1!</Text>
                <Button
                    title="Go Back"
                    onPress={()=>{
                        //返回上一页
                        navigation.goBack();
                    }}/>
            </View>
        );
    }
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
        backgroundColor: '#F5FCFF',
    },
    welcome: {
        fontSize: 20,
        textAlign: 'center',
        margin: 10,
    },
    instructions: {
        textAlign: 'center',
        color: '#333333',
        marginBottom: 5,
    },
});