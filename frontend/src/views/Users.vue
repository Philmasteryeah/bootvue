<template>
  <div class="users">
    <CRow>
      <CCol md="12">
        <CButton color="primary" class="px-4" @click="loadUsers()">Reload users</CButton>
        <CButton @click="modalNew = true" class="px-4" color="success">Create User</CButton>
      </CCol>
    </CRow>
    <CRow>
      <CCol md="12">
        <CCard>
          <CCardHeader>Users</CCardHeader>

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
            >
              <template>
                <td slot="avatar" class="text-center" slot-scope="{item}">
                  <div class="c-avatar">
                    <img :src="item.avatar.url" class="c-avatar-img" alt />
                    <span
                      class="c-avatar-status"
                      :class="`bg-${item.avatar.status || 'secondary'}`"
                    ></span>
                  </div>
                </td>
              </template>
              <template #show_details="{item, index}">
                <td class="py-2">
                  <CButton
                    color="primary"
                    variant="outline"
                    square
                    size="sm"
                    @click="toggleDetails(item, index)"
                  >{{Boolean(item._toggled) ? 'Hide' : 'Show'}}</CButton>
                </td>
              </template>
              <template #details="{item}">
                <CButton @click="modalEdit = true" class="px-4" color="info">Edit</CButton>
                <CModal title="Edit" color="success" :show.sync="modalEdit">
                  <CCardBody>
                    <CMedia :aside-image-props="{ height: 102 }">
                      <h4>{{item.firstName}}</h4>
                      <p class="text-muted">User since: {{item.registered}}</p>
                      <CButton size="sm" color="info" class>User Settings</CButton>
                      <CButton size="sm" color="danger" class="ml-1">Delete</CButton>
                    </CMedia>
                  </CCardBody>
                </CModal>
              </template>
            </CDataTable>
          </CCardBody>
        </CCard>
      </CCol>
    </CRow>
    <CRow>
      <CCol md="12">
        <CModal title="New User" color="success" :show.sync="modalNew">
          <usercomp />
        </CModal>
      </CCol>
    </CRow>

    <CRow>
      <CCol md="12">
        <hellocomp hellomsg="Testparam" />
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
      modalEdit: false,
      modalNew: false,
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
  beforeMount() {
    this.loadUsers();
  },
};
</script>
