<template>
  <div class="users">
    <CRow>
      <CCol md="12">
        <CCard>
          <CCardHeader>Users</CCardHeader>

          <CCardBody>
            <CButton color="primary" @click="loadUsers()">Load User List</CButton>
            <CDataTable
              class="mb-0 table-outline"
              hover
              :items="tableItems"
              :fields="tableFields"
              head-color="light"
              no-sorting
            >
              <td slot="avatar" class="text-center" slot-scope="{item}">
                <div class="c-avatar">
                  <img :src="item.avatar.url" class="c-avatar-img" alt />
                  <span class="c-avatar-status" :class="`bg-${item.avatar.status || 'secondary'}`"></span>
                </div>
              </td>
            </CDataTable>
          </CCardBody>
        </CCard>
      </CCol>
    </CRow>
    <CRow>
      <CCol md="12">
        <usercomp />
      </CCol>
    </CRow>

        <CRow>
      <CCol md="12">
        <hellocomp  hellomsg="Testparam" />
      </CCol>
    </CRow>
  </div>
</template>

<script>
import api from "./backend-api";
import usercomp from "./User";
import hellocomp from "./Hello";

export default {
  name: "Users",
  components: { usercomp, hellocomp },
  data() {
    return {
      selected: "Month",
      tableItems: [],
      tableFields: [
        { key: "id" },
        { key: "username" },
        { key: "firstName" },
        { key: "lastName" },
      ],
    };
  },
  methods: {
    loadUsers() {
      api
        .getUsers()
        .then((response) => {
          this.tableItems = response.data;
        })
        .catch((error) => {
          this.errors.push(error);
        });
      // [
      //   {
      //     id: 1,
      //     firstName: "bla",
      //     lastName: "bla"
      //   }
      // ];
    },
  },
};
</script>
