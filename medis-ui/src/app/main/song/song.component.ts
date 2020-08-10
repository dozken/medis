import { Component, OnInit } from '@angular/core';
import { SongService } from './song.service';
import { Song, GenreType, SongSearch } from './song.model';
import { FormGroup, FormBuilder } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { SongDialogComponent } from './song-dialog/song-dialog.component';
import { ConfirmDialogComponent } from 'app/system/shared/confirm-dialog.component';

@Component({
  selector: 'app-song-component',
  styleUrls: ['./song.component.scss'],
  templateUrl: './song.component.html',
})
export class SongComponent implements OnInit {
  songs: Song[];
  searchDTO: SongSearch;
  public songKeys = Object.keys(GenreType);

  constructor(private service: SongService, private formBuilder: FormBuilder, private modalService: NgbModal) {}

  ngOnInit() {
    this.searchDTO = new SongSearch();
    this.doSearch();
  }

  doNew() {
    const modalRef = this.modalService.open(SongDialogComponent);
    modalRef.componentInstance.model = new Song();
    modalRef.result.then(result => {
      if (result) {
        this.doSearch();
      }
    });
  }
  doEdit(row: Song): void {
    const modalRef = this.modalService.open(SongDialogComponent);
    modalRef.componentInstance.model = row;
    modalRef.result.then(result => {
      if (result) {
        this.doSearch();
      }
    });
  }

  doDelete(row: Song): void {
    const modalRef = this.modalService.open(ConfirmDialogComponent);
    modalRef.componentInstance.text = 'Are you sure you want to delete ' + row.name + '?';
    modalRef.result.then(result => {
      if (result && result.action && result.action === 'yes') {
        this.service.delete(row).subscribe(() => this.doSearch());
      }
    });
  }

  doSearch(): void {
    this.service.search(this.searchDTO).subscribe(results => {
      this.songs = results.body;
    });
  }
}
