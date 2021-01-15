<template>
    <v-container pa-0 ma-0>
        <!--
        =====================================================================================
          Dropdown - Filter ONLY (md-up)
        =====================================================================================
        -->
        <v-layout v-if="!isSort" row align-center justify-start px-2 mt-0 hidden-sm-and-down>
            <p class="pr-1 info--text">{{ text }}</p>
            <v-menu v-model="show" offset-y open-on-hover>
                <template #activator="{ on }">
                    <v-btn color="black" class="text-capitalize font-weight-regular mx-1 my-0 filter-select-btn" small flat v-on="on">
                        <v-layout row justify-space-between align-center px-2>
                            <span class="text-capitalize"> {{ textMenu }} </span>
                            <v-icon class="small-global-icon-font secondary--text" right>{{ iconDesktop }}</v-icon>
                        </v-layout>
                    </v-btn>
                </template>
                <v-list>
                    <v-list-tile v-for="item in items" :key="item.id" class="filter-tile" @click="filterAction(item.id)">
                        <v-layout row align-center justify-space-between>
                            <v-flex xs10>
                                <p :class="[item.id === selected ? 'font-weight-medium' : '', 'body-1 text-capitalize']">{{ item.text }}</p>
                            </v-flex>
                            <v-flex xs2>
                                <v-icon v-if="item.id === selected" class="small-global-icon-font secondary--text fas fa-check-circle" />
                            </v-flex>
                        </v-layout>
                    </v-list-tile>
                </v-list>
            </v-menu>
        </v-layout>
        <!--
        =====================================================================================
          Mobile (sm-xs)
        =====================================================================================
        -->
        <v-layout hidden-md-and-up row wrap align-center justify-start px-1>
            <v-flex v-for="(item, index) in filterArray" :key="index" xs6 sm3 pa-1>
                <!--
                =====================================================================================
                  Filter
                =====================================================================================
                -->
                <v-btn
                    v-if="!isSort"
                    depressed
                    block
                    :color="btnColorFilter(item)"
                    class="text-capitalize font-weight-regular my-0 ml-0 py-0 px-2 ma-0"
                    @click="filterAction(item.id)"
                >
                    <v-layout row align-center justify-start px-2>
                        <v-flex xs10 pl-2 pr-0 py-0>
                            <p :class="[item.id === selected ? 'white--text' : 'secondary--text', 'caption font-weight-medium text-xs-left']">
                                {{ item.text }}
                            </p>
                        </v-flex>
                        <v-flex xs2 pa-0>
                            <v-icon v-if="item.id === selected" :class="'white--text small-global-icon-font fas fa-check-circle'" />
                        </v-flex>
                    </v-layout>
                </v-btn>
                <!--
                =====================================================================================
                  Sort
                =====================================================================================
                -->
                <v-btn
                    v-else
                    depressed
                    block
                    :color="btnColorSort(item)"
                    class="text-capitalize font-weight-regular my-0 ml-0 py-0 px-2 ma-0"
                    @click="sortAction(item)"
                >
                    <v-layout row align-center justify-start px-2>
                        <v-flex v-if="!item.ids.includes(selected.toString())" xs2 pa-0>
                            <v-icon class="secondary--text small-global-icon-font fas fa-check-circle fas fa-sort-amount-up" />
                        </v-flex>
                        <v-flex v-else xs2 pa-0>
                            <v-icon
                                :class="[
                                    selected.includes('high') ? 'fas fa-sort-amount-down' : 'fas fa-sort-amount-up',
                                    'white--text small-global-icon-font fas fa-check-circle '
                                ]"
                            />
                        </v-flex>
                        <v-flex xs10 py-0 pl-2 pr-0>
                            <p
                                :class="[
                                    item.ids.includes(selected.toString()) ? 'white--text' : 'secondary--text',
                                    ' font-weight-medium caption text-xs-left'
                                ]"
                            >
                                {{ item.text }}
                            </p>
                        </v-flex>
                    </v-layout>
                </v-btn>
            </v-flex>
        </v-layout>
    </v-container>
</template>

<script lang="ts">
import { Vue, Component, Prop, Watch } from 'vue-property-decorator'
import { FilterSortItem } from '@app/core/components/props'
interface Sorted {
    text: string
    ids: string[]
}

@Component
export default class AppFilter extends Vue {
    /*
  ===================================================================================
    Props
  ===================================================================================
  */
    @Prop({ type: Boolean, default: true }) isSort!: boolean
    @Prop(Array) items!: FilterSortItem[]
    @Prop([Number, String]) selected!: number | string

    /*
  ===================================================================================
    Initial Data
  ===================================================================================
  */

    show = false

    /*
  ===================================================================================
    Computed
  ===================================================================================
  */

    get text(): string {
        return this.isSort ? this.$t('token.sort-by').toString() : this.$t('filter.name').toString()
    }

    get textMenu(): string {
        const found = this.items.find(i => i.id === this.selected)
        return found ? found.text.toString() : this.items[0].toString()
    }

    get iconDesktop(): string {
        return this.show ? 'fas fa-angle-up' : 'fas fa-angle-down'
    }

    get filterArray(): Sorted[] | FilterSortItem[] {
        if (this.isSort) {
            const options = [...this.items]
            const newItems: Sorted[] = []
            options.forEach(i => {
                const filtered = options.filter(e => e.text == i.text)
                if (newItems.filter(e => e.text == i.text).length === 0) {
                    newItems.push({
                        text: i.text.toString(),
                        ids: filtered.map(i => i.id.toString())
                    })
                }
            })
            return newItems
        }
        return this.items
    }

    sortAction(item: Sorted): void {
        if (item.ids.includes(this.selected.toString())) {
            this.$emit('onSelectChange', item.ids.filter(i => i !== this.selected)[0])
        } else {
            this.$emit('onSelectChange', item.ids[0])
        }
    }
    filterAction(item: FilterSortItem): void {
        this.$emit('onSelectChange', item)
    }

    btnColorSort(item: Sorted): string {
        return item.ids.includes(this.selected.toString()) ? 'secondary' : 'bttnGrey'
    }
    btnColorFilter(item): string {
        return item.id === this.selected ? 'secondary' : 'bttnGrey'
    }
}
</script>

<style lang="scss">
.filter-select-btn {
    border: solid 1px #efefef !important;
    min-height: 30px;
    min-width: 120px;
}
</style>
