import React, {Component} from 'react';
import {
    StyleSheet,
    Text,
    View,
    NativeModules,
    Button
} from 'react-native';

import ToastExample from './ToastExample';
import CockView from './CockView';
import FocusedTextView from './FocusedTextView';

export default class App extends Component {
    //当组件挂载之后,去获取Activity传输过来的数据...
    componentDidMount() {
        //进行从Activity中获取数据传输到JS
        NativeModules.IntentModule.dataToJS((msg) => {
                console.log(msg);
                ToastExample.show('JS界面:从Activity中传输过来的数据为:' + msg, ToastExample.SHORT);
            },
            (result) => {
                ToastExample.show('JS界面:错误信息为:' + result, ToastExample.SHORT);
            });
        NativeModules.IntentModule.rnCallNativePromiss("这是RN消息").then(
            (msg) => {
                console.log(msg);
            }
        ).catch(
            (err) => {
                console.log(err);
            }
        );
    }

    render() {
        if (this.props.flag === 'home') {
            return (
                <View style={styles.container}>
                    <Text style={styles.welcome}>
                        RN的界面--{this.props.flag}
                    </Text>
                    {/*<Button
                        title='Toast'
                        onPress={()=>{
                            ToastExample.show("Awesome", ToastExample.SHORT);
                        }}/>*/}
                </View>
            );
        } else {
            return (
                <View style={styles.container}>
                    <FocusedTextView
                        style={{width: 500, height: 100}}
                        size={30}
                        text={"这是RN调用原生控件的跑马灯效果----->啦啦啦啦啦啦阿拉啦啦啦"}/>
                    <Text style={styles.welcome}>
                        RN的界面
                    </Text>
                    <Text style={styles.welcome}>
                        原生传来的数据name:-> {NativeModules.IntentModule.name}
                    </Text>
                    <Text style={styles.welcome}>
                        原生传来的数据msg:-> {NativeModules.IntentModule.msg}
                    </Text>
                    <Button
                        title='Toast'
                        onPress={() => {
                            ToastExample.show("Awesome", ToastExample.SHORT);
                        }}/>
                    <Button
                        title='measureLayout'
                        onPress={() => {
                            ToastExample.measureLayout((msg) => {
                                ToastExample.show(x + '坐标,' + y + '坐标,' + w + '宽,' + h + '高', ToastExample.SHORT);
                            }, (x, y, w, h) => {
                                ToastExample.show(x + '坐标,' + y + '坐标,' + w + '宽,' + h + '高', ToastExample.SHORT);
                            })
                        }}/>
                    {/*<Button
              title='图片选着器'
              onPress={()=>{
                  NativeModules.ImagePickerModule.pickImage(null);
              }}/>*/}
                    <Button
                        title='跳转到原生界面'
                        onPress={() => {
                            NativeModules.IntentModule.startActivityFromJS("com.zxj.myandroidtest.TwoActivity", "我是从JS传过来的参数信息.")
                        }}/>

                    <Button
                        title='跳转到Activity界面,并且等待数据返回...'
                        onPress={() => {
                            NativeModules.IntentModule.startActivityFromJSGetResult("com.zxj.myandroidtest.ThreeActivity",
                                200, (msg) => {
                                    ToastExample.show('JS界面:从Activity中传输过来的数据为:' + msg, ToastExample.SHORT);
                                }, (result) => {
                                    ToastExample.show('JS界面:错误信息为:' + result, ToastExample.SHORT);
                                });

                        }}/>

                    <CockView time={10}/>
                </View>
            );
        }

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
    image: {
        width: 100,
        height: 100
    }
});