import React, {Component} from 'react';
import {
    Platform,
    StyleSheet,
    Text,
    View,
    Image
} from 'react-native';

var LoadingImgs = [
    require('./img/custom_clock_time_01.png'),
    require('./img/custom_clock_time_02.png'),
    require('./img/custom_clock_time_03.png'),
    require('./img/custom_clock_time_04.png'),
];

export default class CockView extends Component {
    //设置默认属性
    static defaultProps = {
        time: 60
    }

    constructor(props) {
        super(props);
        console.log("constructor==")
        this.state = {
            loadImgIndex: 0,
            time: props.time
        }
    }

    componentDidMount() {
        if (this.timer === undefined) {
            this.timer = setInterval(() => {
                let i = this.state.loadImgIndex + 1;
                i = i % LoadingImgs.length;
                this.setState({
                    loadImgIndex: i,
                    time: this.state.time - 1
                })
                if (this.state.time === 0) {
                    clearInterval(this.timer);
                }
                console.log("i==",i)
            }, 1000);
        }
    }

    componentWillUnmount() {
        this.timer && clearInterval(this.timer);
    }

    render() {
        return (
            <View style={{
                justifyContent: 'center',
                alignItems: 'center',
                flexDirection: 'row'
            }}>
                <Image style={{width: 0, height: 0}} 
                    source={require('./img/custom_clock_time_01.png')} />
                <Image style={{width: 0, height: 0}}
                    source={require('./img/custom_clock_time_02.png')} />
                <Image style={{width: 0, height: 0}}
                    source={require('./img/custom_clock_time_03.png')} />
                <Image style={{width: 0, height: 0}}
                source={require('./img/custom_clock_time_04.png')} />
                <Image style={{width: 80, height: 80}}
                       source={LoadingImgs[this.state.loadImgIndex]} />
                <View style={{marginLeft: 20}}>
                    <Text style={{fontSize: 30}}>{this.state.time} 秒</Text>
                </View>
            </View>
        )
    }
}