import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';

import { Observable, Subject } from 'rxjs';

import { Song, SongSearch } from './song.model';

@Injectable({
  providedIn: 'root',
})
export class SongService {
  baseUrl = '/api/song';

  constructor(private http: HttpClient) {
    console.log('Song service created!');
  }

  getAll(): Observable<Song[]> {
    return this.http.get<Song[]>(this.baseUrl);
  }

  delete(song: Song): Observable<HttpResponse<any>> {
    return this.http.delete(this.baseUrl + '/' + song.id, { observe: 'response' });
  }

  create(song: Song): Observable<HttpResponse<Song>> {
    return this.http.post<Song>(this.baseUrl, song, { observe: 'response' });
  }

  update(song: Song): Observable<HttpResponse<Song>> {
    return this.http.put<Song>(this.baseUrl, song, { observe: 'response' });
  }

  search(search: SongSearch): Observable<HttpResponse<Song[]>> {
    return this.http.post<Song[]>(this.baseUrl + '/search', search, { observe: 'response' });
  }
}
