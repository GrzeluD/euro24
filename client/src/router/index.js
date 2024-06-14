import { createRouter, createWebHistory } from 'vue-router';
import Leaderboard from '../components/LeaderBoard.vue';
import Cms from '../components/CmsPage.vue';

const routes = [
    {
        path: '/',
        name: 'Home',
        component: Leaderboard,
        meta: { title: 'Euro24' }
    },
    {
        path: '/skibidisigma',
        name: 'PredictUsers',
        component: Cms,
        meta: { title: 'Skibidi Sigma' }
    }
];

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
});

export default router;