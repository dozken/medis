import { AfterViewInit, Component, OnInit, ViewChild, Input } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Song, GenreType } from '../song.model';
import { SongService } from '../song.service';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-song-dialog-component',
  styleUrls: ['./song-dialog.component.scss'],
  templateUrl: './song-dialog.component.html',
})
export class SongDialogComponent implements OnInit {
  form: FormGroup;
  @Input() model: Song;
  public genreKeys = Object.keys(GenreType);

  constructor(private service: SongService, private formBuilder: FormBuilder, public activeModal: NgbActiveModal) {
    this.createForm();
  }
  ngOnInit(): void {
    this.form.patchValue(this.model);
  }
  private createForm() {
    this.form = this.formBuilder.group({
      id: null,
      name: [null, Validators.required],
      artist: null,
      releaseDate: null,
      genre: [null, Validators.required],
    });
  }
  doSave() {
    if (this.form.value.id) {
      this.service.update(this.form.value).subscribe(result => this.activeModal.close({ action: 'save' }));
    } else {
      this.service.create(this.form.value).subscribe(result => this.activeModal.close({ action: 'save' }));
    }
  }
}
