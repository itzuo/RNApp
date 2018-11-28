import React, {Component} from 'react';
import {
    StyleSheet,
    View,
    Text
} from 'react-native';
import Gloabl from '../Global';
import {StackActions, NavigationActions} from 'react-navigation';

export default class InitPage extends Component {
    componentWillMount() {
        this.props.navigation.dispatch(StackActions.reset({
            index: 0,
            actions: [
                NavigationActions.navigate({routeName : Gloabl.pageIndex})
            ]
        }));
    }

    render() {
        return (
            <View style={styles.container}>
                <Text style={{fontWeight: 'bold',color:'#2d2d2d',padding:50}}>
                    Please check operation and re-enter the interface
                </Text>
            </View>
        )
    }
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
    }
});