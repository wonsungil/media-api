import { createWebHistory, createRouter } from 'vue-router';

const routes = [
  {
    path: '/',
    name: 'index',
    component: () => import('@/components/Index'),
    props: {
      default: true,
      msg : "BBP VUE TEST"
    }
  },
  {
    path: '/google/campaign/list',
    name: 'campaignList',
    component: () => import('@/components/google/CampaignList'),
  },
  {
    path: '/google/adGroup/list',
    name: 'adGroupList',
    component: () => import('@/components/google/AdGroupList'),
  },
  {
    path: '/google/customer/list',
    name: 'customerList',
    component: () => import('@/components/google/CustomerList'),
  },
  {
    path: '/google/customer/list2',
    name: 'customerList2',
    component: () => import('@/components/google/CustomerListTui'),
  },
  {
    path: '/google/ad/list',
    name: 'adList',
    component: () => import('@/components/google/AdList'),
  },
]

export const router = createRouter({
  history: createWebHistory(),
  routes,
})