import Vue from 'vue'
import Vuex from 'vuex'
import api from './views/backend-api'

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        loginSuccess: false,
        loginError: false,
        userName: null,
        userPass: null,
        sidebarShow: 'responsive',
        sidebarMinimize: false
    },
    mutations: {
        login_success(state, payload){
            state.loginSuccess = true;
            state.userName = payload.userName;
            state.userPass = payload.userPass;
        },
        login_error(state, payload){
            state.loginError = true;
            state.userName = payload.userName;
        },
          toggleSidebarDesktop (state) {
            const sidebarOpened = [true, 'responsive'].includes(state.sidebarShow)
            state.sidebarShow = sidebarOpened ? false : 'responsive'
          },
          toggleSidebarMobile (state) {
            const sidebarClosed = [false, 'responsive'].includes(state.sidebarShow)
            state.sidebarShow = sidebarClosed ? true : 'responsive'
          },
          set (state, [variable, value]) {
            state[variable] = value
          }
    },
    actions: {
        login({commit}, {user, password}) {
            return new Promise((resolve, reject) => {
                console.log("Accessing backend with user: '" + user);
                api.getSecured(user, password)
                    .then(response => {
                        console.log("Response: '" + response.data + "' with Statuscode " + response.status);
                        if(response.status == 200) {
                            console.log("Login successful");
                            // place the loginSuccess state into our vuex store
                            commit('login_success', {
                                userName: user,
                                userPass: password
                            });
                        }
                        resolve(response)
                    })
                    .catch(error => {
                        console.log("Error: " + error);
                        // place the loginError state into our vuex store
                        commit('login_error', {
                            userName: user
                        });
                        reject("Invalid credentials!")
                    })
            })
        }
    },
    getters: {
        isLoggedIn: state => state.loginSuccess,
        hasLoginErrored: state => state.loginError,
        getUserName: state => state.userName,
        getUserPass: state => state.userPass
    }
})