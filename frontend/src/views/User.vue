<template>
  <div class="user">
    <CCard>
      <CCardHeader>Create User</CCardHeader>
      <CCardBody>
        <CInput placeholder="first name" autocomplete="first" v-model="user.firstName">
          <template #prepend-content>
            <CIcon name="cil-user" />
          </template>
        </CInput>

        <CInput placeholder="last name" autocomplete="last" v-model="user.lastName">
          <template #prepend-content>
            <CIcon name="cil-user" />
          </template>
        </CInput>

        <CButton color="success" class="px-4" @click="createNewUser()">Create User</CButton>

        <div v-if="showResponse">
          <h6>User created with Id: {{ response.data }}</h6>
        </div>

        <CButton
          color="primary"
          v-if="showResponse"
          @click="retrieveUser()"
        >Retrieve user {{user.id}} data from database</CButton>

        <h4
          v-if="showRetrievedUser"
        >Retrieved User {{retrievedUser.firstName}} {{retrievedUser.lastName}}</h4>
      </CCardBody>
    </CCard>
  </div>
</template>

<script>
import api from "./backend-api";

export default {
  name: "user",

  data() {
    return {
      response: [],
      errors: [],
      user: {
        lastName: "",
        firstName: "",
        id: 0,
      },
      showResponse: false,
      retrievedUser: {},
      showRetrievedUser: false,
    };
  },
  methods: {
    // Fetches posts when the component is created.
    createNewUser() {
      api
        .createUser(this.user.lastName, this.user.firstName)
        .then((response) => {
          // JSON responses are automatically parsed.
          this.response = response;
          this.user.id = response.data;
          console.log("Created new User with Id " + response.data);
          this.showResponse = true;
        })
        .catch((e) => {
          this.errors.push(e);
        });
    },
    retrieveUser() {
      console.log("ydf");
      api
        .getUser(this.user.id)
        .then((response) => {
          // JSON responses are automatically parsed.
          console.log("--.>" + response.data);
          this.retrievedUser = response.data;
          this.showRetrievedUser = true;
        })
        .catch((e) => {
          this.errors.push(e);
        });
    },
  },
};
</script>


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1,
h2 {
  font-weight: normal;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}

a {
  color: #42b983;
}
</style>
