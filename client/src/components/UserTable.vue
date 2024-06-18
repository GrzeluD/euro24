<template>

  <section class="p-2 md:p-8 w-full bg-blueDarkBg border-2 border-blueBorder rounded-2xl scrollbar-hidden overflow-x-auto">
    <div class="pl-10 md:pl-12 flex text-tabsText text-sm md:text-lg mb-4 w-[828px] md:w-[1133px]">
      <span class="w-[72px] md:w-[104px]">Pozycja</span>
      <span class="w-[200px] md:w-[280px]">Gracz</span>
      <span class="w-[200px]">Ilość dokładnych <br>wyników</span>
      <span class="w-[200px]">Trafiony król strzelców <br>(+5 pkt)</span>
      <span class="w-[200px]">Trafiony zwycięzca <br>turnieju (+5 pkt)</span>
      <span class="w-[96px]">Punkty</span>
    </div>
    <div class="w-[828px] md:w-[1133px] flex text-whiteColor text-sm md:text-lg even:bg-blueBg py-4 rounded-lg" v-for="user in users" :key="user.id">
      <span class="pl-1 md:pl-2 w-[56px]" :class="getPositionChangeClass(user)">{{ getPositionChangeText(user) }}</span>
      <span class="w-[72px] md:w-[104px]">{{ user.position }}</span>
      <span class="w-[200px] md:w-[280px]">{{ user.name }}</span>
      <span class="w-[200px]">{{ user.exactMatches }}</span>
      <span class="w-[200px]"><img class="w-6 h-6" :src="getImage(user.predictedTopScorer)" alt="Predicted Winner"></span>
      <span class="w-[200px]"><img class="w-6 h-6" :src="getImage(user.predictedWinner)" alt="Predicted Winner"></span>
      <span class="w-[96px]">{{ user.points }}</span>
    </div>
  </section>
</template>

<script>
export default {
  name: 'UserTable',
  props: {
    users: {
      type: Array
    }
  },
  methods: {
    getPositionChangeText(user) {
      if (user.previousPosition === 0) return "=";
      const diff = user.previousPosition - user.position;
      if (diff > 0) {
        return `+${diff}`;
      } else if (diff < 0) {
        return `${diff}`;
      } else {
        return "=";
      }
    },
    getPositionChangeClass(user) {
      if (user.previousPosition === 0) return;
      const change = user.previousPosition - user.position;
      if (change > 0) {
        return 'up-arrow text-green-500';
      } else if (change < 0) {
        return 'down-arrow text-red-500';
      }
    },
    getImage(isTrue) {
      return isTrue ? require('@/assets/tick.png') : require('@/assets/cross.png');
    }
  }
};
</script>

<style>
.up-arrow {
  font-size: 12px;
  color: #00FF66;
}

.up-arrow::before {
  content: "▲";
  color: #00FF66;
  margin-right: 4px;
}

.down-arrow {
  color: #FF4141;
  font-size: 12px;
}

.down-arrow::before {
  content: "▼";
  color: #FF4141;
  margin-right: 4px;
}

.scrollbar-hidden {
  -ms-overflow-style: none;  /* Internet Explorer 10+ */
  scrollbar-width: none;     /* Firefox */
}
.scrollbar-hidden::-webkit-scrollbar {
  display: none; /* Safari i Chrome */
}
</style>