import {Component, OnInit, ElementRef, ViewChild} from '@angular/core';

import {Item} from '../entities/item';

import {ItemService} from '../services/item.service';

@Component({
  selector: 'app-item',
  templateUrl: './item.component.html',
  styleUrls: ['./item.component.css']
})
export class ItemComponent implements OnInit {

  private item: Item = new Item();
  private dataLoading = false;

  @ViewChild('modalInput')
  private modalInput: ElementRef;

  constructor(private itemService: ItemService) {}

  ngOnInit() {
  }

  onSubmit() {
    this.dataLoading = true;
    this.itemService.create(this.item).subscribe(u => {
      this.item = u;
      this.dataLoading = false;
      this.modalInput.nativeElement.click();
    });
  }
}
