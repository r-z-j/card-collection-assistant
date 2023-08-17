<template>
  <div
    id="register"
    :class="['text-center', 'card', { shake: registrationErrors }]"
  >
    <form @submit.prevent="register">
      <h1>Create Account</h1>
      <div class="error-msg" role="alert" v-if="showRegistrationErrorMsg">
        <p>{{ registrationErrorMsg }}</p>
      </div>
      <div v-else><br/></div>
      <div class="form-input-group">
        <label for="username">Username</label>
        <input
          type="text"
          id="username"
          v-model="user.username"
          required
          autofocus
        />
      </div>
      <div class="form-input-group">
        <label for="password">Password</label>
        <input type="password" id="password" v-model="user.password" required autofocus/>
      </div>
      <div class="form-input-group">
        <label for="confirmPassword">Confirm Password</label>
        <input
          type="password"
          id="confirmPassword"
          v-model="user.confirmPassword"
          required
          autofocus
        />
      </div>
      <button type="submit">Create Account</button>
      <p>
        <router-link :to="{ name: 'login' }"
          >Already have an account? Log in.</router-link
        >
      </p>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "register",
  data() {
    return {
      user: {
        username: "",
        password: "",
        confirmPassword: "",
        role: "user",
      },
      registrationErrors: false,
      showRegistrationErrorMsg: false,
      registrationErrorMsg: "There were problems registering this user.",
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.showRegistrationErrorMsg = true;
        this.registrationErrorMsg = "Password & Confirm Password do not match.";
      } else if (
        this.user.password.length < 8 ||
        this.user.password.length > 15
      ) {
        this.registrationErrors = true;
        this.showRegistrationErrorMsg = true;
        setTimeout(() => {
          this.registrationErrors = false;
        }, 1000);
        this.registrationErrorMsg =
          "Password must be between 8 and 15 characters long.";
      } else {
        this.user.username = this.user.username.toLowerCase();
        this.showRegistrationErrorMsg = false;

        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: "/login",
                query: { registration: "success" },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = "Bad Request: Validation Errors";
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = "There were problems registering this user.";
    },
  },
};
</script>

<style scoped>
#id{
  overflow: hidden;
}
.form-input-group {
  margin-bottom: 3rem;
  display: flex;
  flex-direction: column;
}
label {
  padding-top: 10px;
  margin-right: 0.5rem;
}
input {
  padding: 10px;
  font-size: 14px;
  border: 3px solid #ccc;
  border-radius: 5px;
  width: 250px;
  margin-left: auto;
  margin-right: auto;
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
  color: red;
}
</style>
