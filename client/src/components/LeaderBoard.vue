<template>
  <header class="pt-4 md:pt-12">
    <img src="@/assets/logo.png" alt="logo naszego wspaniałego arriska" class="block w-[286px] md:w-[640px] mb-8 md:mb-[88px] mx-auto">
  </header>
  <main class="flex-1">
    <div>
      <nav class="w-[328px] md:w-[400px] text-lg text-tabsText border-2 border-blueBorder h-12 mx-auto flex rounded-3xl bg-blueDarkBg">
        <button class="w-1/2 relative" @click="activeTab='results'" :class="{ active: activeTab === 'results' }"><span class="relative z-10">Tabela wyników</span></button>
        <button class="w-1/2 relative" @click="activeTab='rules'" :class="{ active: activeTab === 'rules' }"><span class="relative z-10">Zasady</span></button>
      </nav>
      <div class="pt-10 ml-4 w-[calc(100vw_-_16px)] overflow-hidden xl:w-[1200px] xl:mx-auto" v-if="activeTab === 'results'">
        <h2 class="font-heading text-yellowColor text-3xl md:text-6xl text-center mb-8">Tabela wyników</h2>
        <UserTable :users="users" />
      </div>
      <div class="pt-10 w-[calc(100%_-_32px)] xl:w-[1200px] mx-4 xl:mx-auto" v-if="activeTab === 'rules'">
        <h2 class="font-heading text-yellowColor text-3xl md:text-6xl text-center mb-8">Zasady</h2>
        <RulesSection />
      </div>
    </div>
  </main>
  <footer>
    <img src="@/assets/footer-desktop.png" class="w-full" alt="">
  </footer>
</template>
<script>
import UserTable from './UserTable.vue';
import RulesSection from './RulesSection.vue';
import axios from "axios";
import {apiURL} from "@/helper/helper";

export default {
  data() {
    return {
      activeTab: 'results',
      users: []
    };
  },
  components: {
    UserTable,
    RulesSection
  },
  created() {
    this.fetchUsers();
    document.title = 'Euro 2024';
  },
  methods: {
    fetchUsers() {
      axios.get(`${apiURL}/users`)
          .then(response => this.users = response.data.sort((a, b) => a.position - b.position))
          .catch(error => {
            console.log("Zdupiło się :( " + error);
          });
    },
  }
};
</script>
<style>
.active {
  color: #FFF;
}

.active:before {
  content: "";
  z-index: 0;
  position: absolute;
  top: 2px;
  right: 2px;
  bottom: 2px;
  left: 2px;
  border-radius: 24px;
  background: #3145AD;
}
</style>