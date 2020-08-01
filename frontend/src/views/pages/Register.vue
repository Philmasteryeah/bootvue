<template>
  <div class="d-flex align-items-center min-vh-100">
    <CContainer fluid>
      <CRow class="justify-content-center">
        <CCol md="6">
          <CCard class="mx-4 mb-0">
            <CCardBody class="p-4">
              <CForm>
                <h1>Register</h1>
                <p class="text-muted">Create your account</p>
                <CInput placeholder="Username" autocomplete="username" v-model="user.username">
                  <template #prepend-content>
                    <CIcon name="cil-user" />
                  </template>
                </CInput>
                <CInput placeholder="Email" autocomplete="email" prepend="@" v-model="user.email"/>
                <CInput placeholder="Password" type="password" autocomplete="new-password" v-model="user.password">
                  <template #prepend-content>
                    <CIcon name="cil-lock-locked" />
                  </template>
                </CInput>
                <CInput
                  placeholder="Repeat password"
                  type="password"
                  autocomplete="new-password"
                  class="mb-4"
                >
                  <template #prepend-content>
                    <CIcon name="cil-lock-locked" />
                  </template>
                </CInput>
                <CButton color="success" block @click="createNewUser()">Create Account</CButton>
              </CForm>
            </CCardBody>
            <CCardFooter class="p-4">
              <CRow>
                <CCol col="6">
                  <CButton block color="facebook">Facebook</CButton>
                </CCol>
                <CCol col="6">
                  <CButton block color="twitter">Twitter</CButton>
                </CCol>
              </CRow>
            </CCardFooter>
          </CCard>
        </CCol>
      </CRow>
    </CContainer>
  </div>
</template>

<script>
import api from "../backend-api";

export default {
  name: "Register",

  data() {
    return {
      response: [],
      errors: [],
      user: {
        username: "",
        password: "",
        email: "",
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
  }
};
</script>
