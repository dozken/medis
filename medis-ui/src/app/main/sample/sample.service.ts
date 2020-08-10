import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';

import { Observable, Subject } from 'rxjs';

import { Sample, SampleSearch } from './sample.model';

@Injectable({
  providedIn: 'root',
})
export class SampleService {
  baseUrl = '/api/sample';

  constructor(private http: HttpClient) {
    console.log('Sample service created!');
  }

  getAll(): Observable<Sample[]> {
    return this.http.get<Sample[]>(this.baseUrl);
  }

  delete(sample: Sample): Observable<HttpResponse<any>> {
    return this.http.delete(this.baseUrl + '/' + sample.id, { observe: 'response' });
  }

  create(sample: Sample): Observable<HttpResponse<Sample>> {
    return this.http.post<Sample>(this.baseUrl, sample, { observe: 'response' });
  }

  update(sample: Sample): Observable<HttpResponse<Sample>> {
    return this.http.put<Sample>(this.baseUrl, sample, { observe: 'response' });
  }

  search(search: SampleSearch): Observable<HttpResponse<Sample[]>> {
    return this.http.post<Sample[]>(this.baseUrl + '/search', search, { observe: 'response' });
  }
}
