import {Component, OnInit, ElementRef, ViewChild} from '@angular/core';

import {ItemService} from '../services/item.service';
import {Item} from '../entities/item';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  //    items: Item[] = [
  //      {id: 11, description: 'Mr. Nice', itemType: 1, value: 12.345, itemDate: '2018-01-10'},
  //      {id: 12, description: 'Narco', itemType: 0, value: 1234.5, itemDate: '2018-01-10'},
  //      {id: 13, description: 'Bombasto', itemType: 0, value: 1.2345, itemDate: '2018-01-10'},
  //      {id: 14, description: 'Celeritas', itemType: 0, value: 12345, itemDate: '2018-01-10'},
  //      {id: 15, description: 'Magneta', itemType: 1, value: 12345, itemDate: '2018-01-10'}
  //    ];

  items: Item[];

  type = 'pie3d';
  dataFormat = 'json';
  dataSource = {chart: {}, data: {}};

  idDelete: number;

  @ViewChild('modalInput')
  private modalInput: ElementRef;

  constructor(private itemService: ItemService) {

    this.dataSource.chart = {
      'caption': 'Relação receitas x despesas',
      'startingangle': '120',
      'showlabels': '0',
      'showlegend': '1',
      'enablemultislicing': '0',
      'slicingdistance': '15',
      'showpercentvalues': '1',
      'showpercentintooltip': '0',
      'plottooltext': 'Total $label : $datavalue',
      'theme': 'ocean'
    };

  }

  ngOnInit() {
    this.getItems();
  }

  getItems(): void {
    this.itemService.getItems()
      .subscribe(items => {
        this.items = items;

        let valueReceitas = 0;
        let valueDespesas = 0;

        items.forEach(i => {
          i.itemType === 1 ? valueReceitas += i.value : valueDespesas += i.value;
        });

        this.dataSource.data = [{
          'label': 'Receitas',
          'value': valueReceitas,
          'color': '#6baa01'
        },
        {
          'label': 'Despesas',
          'value': valueDespesas,
          'color': '#e44a00'
        }];

      });
  }

  confirmation(id: number) {
    this.idDelete = id;
    this.modalInput.nativeElement.click();
  }

  deleteItem() {
    this.itemService.deleteItem(this.idDelete)
      .subscribe(res => this.getItems());
  }
}
