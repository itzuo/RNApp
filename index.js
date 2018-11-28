import { AppRegistry } from 'react-native';
import App from './App';
import App1 from './App1';
import MyTest from './MyTest';

AppRegistry.registerComponent('RNApp', () => App1);
AppRegistry.registerComponent('App', () => App);
AppRegistry.registerComponent('MyTest', () => MyTest);