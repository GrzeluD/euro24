<template>
  <header class="p-4 w-full">
    <img src="@/assets/logo.png" alt="logo naszego wspaniałego arriska" class="block w-[286px] mx-auto">
  </header>
  <main class="flex-1 bg-whiteColor pb-10">
    <div class="w-[1200px] pt-6 mx-auto flex gap-6 flex-wrap">
      <div class="relative mb-0">
        <div class="flex w-[880px] h-[218px] gap-6 flex-wrap items-start">
          <MatchWrapper @update-matches="updateMatches" :matches="matches" />
        </div>
      </div>
      <img class="w-[296px] h-[322px]" src="@/assets/lewy.jpg" alt="lewy po meczu tenisa">
    </div>
    <UserWrapper @update-users="updateUsers" :users="users" :matches="matches" />
    <div class="justify-center mt-8 flex gap-6" v-if="users.length > 0 && matches.length > 0">
      <input type="password" class="rounded-md w-[240px] px-2 text-center h-[48px] border-blueBg border-[1px]" v-model="password" placeholder="Wprowadź hasło">
      <button class="w-[240px] rounded-3xl text-center h-[48px] leading-[48px] bg-blueBg text-whiteColor" @click="submitPredictions">Wyślij</button>
    </div>
  </main>
</template>

<script>
import axios from 'axios';
import MatchWrapper from "@/components/MatchWrapper.vue";
import UserWrapper from "@/components/UsersWrapper.vue";
import {apiURL} from "@/helper/helper";

export default {
  components: {UserWrapper, MatchWrapper},
  data() {
    return {
      matches: [],
      users: [],
      password: ''
    };
  },
  created() {
    document.title = 'Skibidi Sigma';
  },
  methods: {
    updateMatches(newMatches) {
      this.matches = newMatches;
    },
    updateUsers(newUsers) {
      this.users = newUsers;
    },
    submitPredictions() {
      const correctPassword = 'skibiditoilet';

      if (this.password !== correctPassword) {
        alert('Niepoprawne hasło');
        return;
      }

      axios.post(`${apiURL}/users/processMatches`, {
        matches: this.matches,
        users: this.users
      })
          .then(response => {
            console.log(response);
            this.matches = [];
            this.users = [];
          })
          .catch(error => {
            console.log(error);
          });
    }
  }
};
</script>

<style scoped>

</style>