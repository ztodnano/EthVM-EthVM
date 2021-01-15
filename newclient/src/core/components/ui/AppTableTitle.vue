<template>
    <div class="title-x">
        <!--
        =====================================================================================
          Home Page
        =====================================================================================
        -->
        <v-layout v-if="isHome" align-center justify-space-between row wrap fill-height pb-1 px-2>
            <v-flex xs6>
                <v-card-title class="title font-weight-bold pl-1 py-2">{{ title }}</v-card-title>
            </v-flex>
            <v-spacer />
            <v-flex shrink>
                <v-btn :to="pageLink" outline color="secondary" class="text-capitalize ma-0">{{ $t('btn.view-all') }}</v-btn>
            </v-flex>
        </v-layout>
        <!--
        =====================================================================================
          All Other Pages
        =====================================================================================
        -->

        <div v-else>
            <!--
        =====================================================================================
          Tablet/Desktop (sm - xl)
        =====================================================================================
        -->
            <v-layout align-center justify-center row wrap pa-2 hidden-sm-and-down>
                <!--
        =====================================================================================
          Title / Subtitle / Update
        =====================================================================================
        -->
                <v-flex xs12 :class="[titleClass, 'title-table']">
                    <v-layout align-center justify-start row wrap pa-1>
                        <v-card-title class="title font-weight-bold pl-1">{{ title }} </v-card-title>
                        <p v-if="hasCaption" class="info--text pl-1">{{ titleCaption }}</p>
                        <slot name="update" />
                    </v-layout>
                </v-flex>
                <v-flex xs12>
                    <v-layout row align-center justify-start pb-2>
                        <v-flex v-if="hasFilter" xs6 py-1>
                            <slot name="filter" />
                        </v-flex>
                        <v-flex v-if="hasPagination" xs6>
                            <v-layout align-end justify-end row px-2>
                                <slot name="pagination" />
                            </v-layout>
                        </v-flex>
                    </v-layout>
                </v-flex>
            </v-layout>
            <v-layout align-center justify-start row wrap px-2 mb-2 hidden-md-and-up>
                <v-flex xs12 :class="titleClass" pb-2>
                    <v-layout align-center justify-start row wrap pa-1>
                        <v-card-title class="title font-weight-bold pl-1 pb-2">{{ title }} </v-card-title>
                        <p v-if="hasCaption" class="info--text pl-1">{{ titleCaption }}</p>
                        <slot name="update" />
                    </v-layout>
                </v-flex>
                <v-flex v-if="hasFilter" sm8 xs12 px-2 py-1>
                    <slot name="filter" />
                </v-flex>
                <v-flex v-if="hasPagination" sm4 xs12 py-1>
                    <v-layout align-end justify-end row px-2>
                        <slot name="pagination" />
                    </v-layout>
                </v-flex>
            </v-layout>
        </div>
    </div>
</template>
<script lang="ts">
import { Component, Prop, Vue } from 'vue-property-decorator'

@Component
export default class AppTableTitle extends Vue {
    /*
    ===================================================================================
      Props
    ==================================================================================
  */

    @Prop(String) title?: string
    @Prop(String) pageLink?: string
    @Prop({ type: String, default: 'home' }) pageType!: string
    @Prop({ type: String, default: '' }) titleCaption!: string
    @Prop({ type: Boolean, default: false }) hasPagination!: boolean
    @Prop({ type: Boolean, default: false }) hasFilter!: boolean

    /*
    ===================================================================================
      Computed
    ==================================================================================
  */

    get isHome(): boolean {
        return this.pageType === 'home'
    }

    get hasCaption(): boolean {
        return this.titleCaption !== ''
    }
    get titleClass(): string {
        return this.$vuetify.breakpoint.name === 'xs' ? 'py-1 px-2' : 'pa-2'
    }

    get shrinkPagination(): boolean {
        return this.$vuetify.breakpoint.name !== 'xs'
    }
}
</script>

<style scoped lang="css">
.title-table {
    min-height: 60px;
}
.table-blocks-header-card {
    margin-right: 1px;
}
.table-row-mobile {
    border: 1px solid #b4bfd2;
}
.title-x {
    min-height: 70px;
}
</style>
