import {createStackNavigator,createAppContainer} from 'react-navigation';

import HomePage from './page/HomePage';
import Page1 from './page/Page1';
import Page2 from './page/Page2';
import InitPage from './page/InitPage';
/**
 * 导航器
 */
export const AppStackNavigator = createStackNavigator({
    InitPage:InitPage,
        HomePage: HomePage,
        Page1:Page1,
        Page2:Page2
});

// export default createAppContainer(AppStackNavigator);