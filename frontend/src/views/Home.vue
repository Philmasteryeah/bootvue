<template>
  <div>
    <h1>
      <CCard variant="success">YEAH you made it!</CCard>
    </h1>
    <h5>If you're able to read this, you've successfully logged in and redirected to this protected site :)</h5>
        <CButton color="success" class="px-4" @click="getSecuredTextFromBackend()">Call the secured API</CButton>
    
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
      securedApiCallSuccess: false,
      errors: null,
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
  },
};
</script>

