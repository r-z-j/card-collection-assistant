<template>
  <div id="login" :class="['card', { shake: invalidCredentials }]">
    <form @submit.prevent="login">
      <h1 >Please Sign In</h1>
      <div class="error-msg" role="alert" v-if="showErrorMessage">
        Invalid username or password!
      </div>
      <div role="alert" v-if="this.$route.query.registration">
        Thank you for registering, please sign in.
      </div>
      <div class="form-input-group">
        <label for="username">Username:</label>
        <input type="text" id="username" v-model="user.username" required autofocus />
      </div>
      <div class="form-input-group">
        <label for="password">Password:</label>
        <input type="password" id="password" v-model="user.password" required />
      </div>
      <button type="submit">Sign in</button>
      <p>
      <router-link :to="{ name: 'register' }">Need an account? Sign up.</router-link></p>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false,
      showErrorMessage: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
            this.showErrorMessage = true;
            setTimeout(() => {
              this.invalidCredentials = false;
            }, 1000);
          }
        });
    }
  }
};
</script>

<style scoped>
.form-input-group {
  margin-bottom: 3rem;
  display: flex;
  flex-direction: column;
}
label {
  padding-top: 20px;
  margin-right: 0.5rem;
}

input {
  padding: 10px;
  font-size: 14px;
  border: 3px solid #ccc;
  border-radius: 5px;
  width: 250px;
  transition: border-color 0.3s ease;
}
input:focus {
  outline: none;
  border-color: #270cbd;
}

button {
  padding: 10px 20px;
  font-size: 16px;
  background-color: #6200ff;
  color: white;
  border: 2px solid #270cbd;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

button:hover {
  background-color: #3e049d;
}
.error-msg {
  color: red
}
</style>