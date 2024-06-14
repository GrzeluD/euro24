<template>
  <div class="w-full p-4 md:p-8 bg-blueDarkBg rounded-xl border-2 border-blueBorder">
    <ul>
      <li class="flex text-whiteColor mb-10"><span class="font-heading text-yellowColor text-4xl md:text-6xl w-[56px] md:w-[100px]">1.</span> <p class="text-justify text-base md:text-3xl flex-1">Wyniki meczów podajemy najpóźniej <b>do momentu startu</b> typowanego spotkania. W przypadku opóźnienia w typowaniu spotkania mecz liczony jest za <b>0 punktów</b>.</p></li>
      <li class="flex text-whiteColor mb-10"><span class="font-heading text-yellowColor text-4xl md:text-6xl w-[56px] md:w-[100px]">2.</span> <p class="text-justify text-base md:text-3xl flex-1">Króla strzelców oraz zwycięzce turnieju typujemy <b>przed rozpoczęciem</b> Mistrzostw Europy.</p></li>
      <li class="flex text-whiteColor mb-10"><span class="font-heading text-yellowColor text-4xl md:text-6xl w-[56px] md:w-[100px]">3.</span> <p class="text-justify text-base md:text-3xl flex-1">Za wytypowanie prawidłowo największej ilości dokładnych wyników, króla strzelców oraz zwycięzcy turnieju przyznawane jest po <b>5 punktów bonusowych</b>. (Punkty doliczane są na koniec turnieju)</p></li>
      <li class="flex text-whiteColor mb-10"><span class="font-heading text-yellowColor text-4xl md:text-6xl w-[56px] md:w-[100px]">4.</span> <p class="text-justify text-base md:text-3xl flex-1">Dokładny wynik jest punktowany za <b>3 punkty</b>, wytypowanie zwycięzcy za <b>1 punkt. Nietrafiony wynik za 0 punktów</b>.</p></li>
      <li class="flex text-whiteColor"><span class="font-heading text-yellowColor text-4xl md:text-6xl w-[56px] md:w-[100px]">5.</span> <p class="text-justify text-base md:text-3xl flex-1">W fazie pucharowej obowiązuje wynik do 120 minuty spotkania. W przypadku wytypowania remisu dopisujemy, która drużyna wygra rzuty karne. W tym wypadku można zdobyć aż <b>4 punkty</b> (<b>3 punkty</b> za dokładny wynik oraz <b>1 punkt</b> za trafionego zwycięzce karnych).</p></li>
    </ul>
  </div>
</template>

<script>
export default {
  name: 'RulesSection'
};
</script>