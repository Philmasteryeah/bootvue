<template>
  <div>
    <CRow>
      <CCol md="12">
        <h1>
          <CCard variant="success">YEAH you made it!</CCard>
        </h1>
        <CButton color="primary" class="px-4" @click="test()">load auths</CButton>
        <h5>If you're able to read this, you've successfully logged in and redirected to this protected site :)</h5>
        <CButton
          color="success"
          class="px-4"
          @click="getSecuredTextFromBackend()"
        >Call the secured API</CButton>
        <p></p>
        <div v-if="securedApiCallSuccess">
          <CCard variant="success">API call</CCard>
          Full response: {{ backendResponse }}
          <CCard variant="success">successful</CCard>
        </div>
        <div v-if="errors">
          <CCard variant="warning">API call</CCard>
          {{ errors }}
          <CCard variant="warning">NOT successful</CCard>
        </div>
      </CCol>
    </CRow>
    <CRow>
      <CCol md="12">
        <CButton color="primary" class="px-4" @click="loadAuthorities()">Reload Authorities</CButton>
        <CButton @click="modalNew = true" class="px-4" color="success">Create Authority</CButton>
      </CCol>
    </CRow>
    <CRow>
      <CCol md="12">
        <CCard>
          <CCardHeader>Authorities</CCardHeader>

          <CCardBody>
            <CDataTable
              class="mb-0 table-outline"
              :items="tableItems"
              :fields="tableFields"
              head-color="light"
              column-filter
              table-filter
              items-per-page-select
              :items-per-page="5"
              hover
              sorter
              pagination
            ></CDataTable>
          </CCardBody>
        </CCard>
      </CCol>
    </CRow>
    <CRow>
      <CCol md="12">
        <CModal title="New Authority" color="success" :show.sync="modalNew">
          <template>
            <div class="user">
              <CCard>
                <CCardHeader>Create Authority</CCardHeader>
                <CCardBody>
                  <CInput placeholder="name " autocomplete="name" v-model="authority.name">
                    <template #prepend-content>
                      <CIcon name="cil-user" />
                    </template>
                  </CInput>

                  <CButton
                    color="success"
                    class="px-4"
                    @click="createNewAuthority()"
                  >Create Authority</CButton>

                  <div v-if="showResponse">
                    <h6>User created with Id: {{ response.data }}</h6>
                  </div>
                </CCardBody>
              </CCard>
            </div>
          </template>
        </CModal>
      </CCol>
    </CRow>
  </div>
</template>
          

        </CModal>
      </CCol>
    </CRow>
  </div>
</template>

<script>
import api from "./backend-api";
import store from "./../store";

export default {
  name: "Home",

  data() {
    return {
      backendResponse: "",
      response: [],
      errors: [],
      securedApiCallSuccess: false,
      showResponse: false,
      errors: null,
      modalNew: false,
      tableItems: [],
      tableFields: [{ key: "name" }],
      authority: {
        name: "",
      },
    };
  },
  methods: {
    getSecuredTextFromBackend() {
      api
        .getSecured(store.getters.getUserName, store.getters.getUserPass)
        .then((response) => {
          console.log(
            "Response: '" +
              response.data +
              "' with Statuscode " +
              response.status
          );
          this.securedApiCallSuccess = true;
          this.backendResponse = response.data;
        })
        .catch((error) => {
          console.log("Error: " + error);
          this.errors = error;
        });
    },
    loadAuthorities() {
      api
        .getAuthorities()
        .then((response) => {
          this.tableItems = response.data;
          console.log(response);
        })
        .catch((error) => {
          console.log(error);
          //this.errors.push(error);
        });
    },
    createNewAuthority() {
      api
        .createAuthority(this.authority.name)
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
  },
};
</script>

