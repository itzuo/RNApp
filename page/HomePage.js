import React, {Component} from 'react';
import {Platform, StyleSheet, Text, View, Button, Alert} from 'react-native';

export default class HomePage extends Component {
    render() {
        //从props里面取得navigation
        const {navigation} = this.props;
        return (
            <View style={styles.container}>
                <Text style={styles.welcome}>Welcome to HomePage!</Text>
                <Button
                    title="跳转到Page1"
                    onPress={() => {
                        navigation.navigate('Page1')
                    }}/>

                <View style={{marginTop: 10}}>
                    <Button
                        title="跳转到Page2"
                        onPress={() => {
                            navigation.navigate('Page2')
                        }}/>
                </View>
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