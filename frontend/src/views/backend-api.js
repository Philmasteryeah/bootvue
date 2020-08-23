import axios from 'axios'

const AXIOS = axios.create({
    timeout: 1000
});

const AXIOS2 = axios.create({
    headers: { 'Content-Type': 'application/json' },
    timeout: 1000
});


export default {
    hello() {
        return AXIOS.get(`/hello`);
    },
    getUsers() {
        return AXIOS2.get(`/accounts`);
    },
    getAuthorities() {
        return AXIOS.get(`/authorities`);
    },
    createAuthority(name) {
        return AXIOS.post(`/authority/` + name);
    },
    getUser(userId) {
        return AXIOS2.get(`/accounts/` + userId);
    },
    createUser(lastName, firstName) {
        return AXIOS2.post('/accounts/', {
            firstName: firstName,
            lastName: lastName
        }).then((res) => {
            console.log("RESPONSE ==== : ", res);
        }).catch((err) => {
            console.log("ERROR: ====", err);
        });

        // return AXIOS.post(`/user/` + lastName + '/' + firstName);
    },
    getSecured(user, password) {
        return AXIOS.get(`/secured/`, {
            auth: {
                username: user,
                password: password
            }
        });
    }
}


