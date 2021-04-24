import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort, Sort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { NotesService } from '../notes.service';

@Component({
  selector: 'app-notes-list',
  templateUrl: './notes-list.component.html',
  styleUrls: ['./notes-list.component.scss']
})
export class NotesListComponent implements OnInit, AfterViewInit {

  dataSource = new MatTableDataSource<any>([]);

  textFilter = '';
  userFilter = '';
  valueFilter : any = null;

  textSort = 'asc';

  pageNumber = 0;
  pageSize = 5;
  totalRecords = 0;

  @ViewChild(MatSort) sort : MatSort;
  @ViewChild(MatPaginator) paginator : MatPaginator;

  constructor(private noteService : NotesService) { }

  ngOnInit(): void {

  }

  ngAfterViewInit(){
    this.loadData();
  }

  loadData(){

    this.pageNumber = this.paginator.pageIndex;
    this.pageSize = this.paginator.pageSize;

    this.noteService.getNotes(this.textFilter, this.userFilter, this.valueFilter, this.textSort, this.pageNumber, this.pageSize).subscribe(
      (data : any) =>{
        this.dataSource.data = data.content;
        this.totalRecords = data.totalElements;
      }, (error : any) =>{
        alert('Error');
      }
    );
  }

  sortNotes(event: Sort){
    if(!event.active || event.direction === ''){
      this.textSort == '';
    }
    else{
      this.textSort = event.active + ',' + this.sort.direction;
    }

    this.loadData();
  }

}
