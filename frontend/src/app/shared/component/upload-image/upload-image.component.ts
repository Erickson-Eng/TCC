import { Component, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-upload-image',
  templateUrl: './upload-image.component.html',
  styleUrls: ['./upload-image.component.scss'],
})
export class UploadImageComponent {
  @Input() acceptedTypes: string[] = [];
  @Output() fileSelected: EventEmitter<File> = new EventEmitter();

  selectedFile: File | null = null;

  onFileSelected(event: any, acceptedTypes: string[]) {
    const file: File = event.target.files[0];
    if (acceptedTypes.length > 0 && !acceptedTypes.includes(file.type)) {
      console.log('Arquivo não permitido');
      return;
    }
    this.selectedFile = file;
    this.fileSelected.emit(file);
  }
}
