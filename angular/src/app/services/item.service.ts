import {Injectable} from '@angular/core';
import {Http} from '@angular/http';

import {Item} from '../entities/item';

import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

@Injectable()
export class ItemService {

  constructor(private http: Http) {}

  create(item: Item): Observable<Item> {
    return this.http.post('/financial-control/item/add/', item)
      .map(res => res.json())
      .catch(err => {
        throw new Error(err.message);
      });

  }

  getItems(): Observable<Item[]> {
    return this.http.get('/financial-control/item/all/')
      .map(res => res.json())
      .catch(err => {
        throw new Error(err.message);
      });

  }

  deleteItem(id: number): Observable<String> {
    return this.http.delete('/financial-control/item/del/' + id)
      .map(res => res.json())
      .catch(err => {
        throw new Error(err.message);
      });
  }

}
