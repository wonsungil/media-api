<template>
  <el-table :data="data" style="width: 100%">
    <el-table-column prop="id" label="id" width="180" />
    <el-table-column prop="type" label="type" width="180" />
    <el-table-column prop="finalUrls" label="finalUrls" />
    <el-table-column prop="resourceName" label="resourceName" />
    <el-table-column>
      <template #default="scope">
        <div v-if="scope.row.type === 'RESPONSIVE_DISPLAY_AD'">
          {{ scope.row.responsiveDisplayAd }}
        </div>
        <div v-else-if="scope.row.type === 'APP_AD'">
          {{ scope.row.appAd }}
        </div>
        <div v-else-if="scope.row.type === 'IMAGE_AD'">
          {{ scope.row.imageAd }}
        </div>
        <div v-else>
          {{ scope.row }}
        </div>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
export default {
  data() {
    return {
      data : null,
    }
  },
  mounted() {
    console.log("mounted");
    this.getCampaignList();
  },
  computed : {
  },
  methods : {
    getCampaignList() {
      this.$axios.get('/api/google/ad-group-ad/ads/6583317631')
          .then(res => {
            console.log(res.data);
            this.data = res.data;
          }).catch(error => {
        console.log(error);
      })
    },
  }
}
</script>
<style lang="scss">
</style>