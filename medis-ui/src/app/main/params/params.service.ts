import { Injectable } from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';


import {Observable, Subject} from 'rxjs';

import { Parameter, ParameterSearch} from './parameter.model';
import { Params } from '@angular/router';

@Injectable({
    providedIn: 'root'
})
export class ParamsService{
baseUrl = '/api';
// public  updateUi =  new Subject<any>();
    constructor(
              private http: HttpClient,
        ) {
        console.log('Params service created!');
    }

    getAll(): Observable<any> {
        return this.http.get(`${this.baseUrl}` + '/parameter');
    }

    delete(id: any): Observable<HttpResponse<any>> {
        return this.http.delete(this.baseUrl + '/parameter/' + id, { observe: 'response' });
    }

    getById(id: any): Observable<any> {
        return this.http.get(this.baseUrl + '/parameter/' + id);
    }
    create(params: Parameter): Observable<HttpResponse<Parameter>> {
        return this.http.post<Parameter>(this.baseUrl + '/parameter', params, { observe: 'response' });
    }

    update(params: Parameter): Observable<HttpResponse<Parameter>> {
        return this.http.put<Parameter>(this.baseUrl + '/parameter', params, { observe: 'response' });
    }
/*
    // search(search: SampleSearch): Observable<HttpResponse<Sample[]>> {
    //     return this.http.post<Sample[]>(this.baseUrl + '/search', search, { observe: 'response' });
    // }
*/
  search(search: ParameterSearch): Observable<HttpResponse<Params[]>> {
    return this.http.post<Params[]>(this.baseUrl + '/parameter/search', search, { observe: 'response' });
  }
}
