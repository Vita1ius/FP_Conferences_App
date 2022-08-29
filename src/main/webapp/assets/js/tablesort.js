const table = document.querySelector('#tableSort');
let colIndex = -1;

const sortTable = function (index,type,isSorted){
    const tbody = table.querySelector('tbody');
    const compare = function (rowA,rowB){
        const rowDataA = rowA.cells[index].innerHTML;
        const rowDataB = rowB.cells[index].innerHTML;

        switch (type){
            case 'integer':
                return rowDataA - rowDataB;
                break;
            case 'date':
                const dateA = rowDataA.split('-').join('-');
                const dateB = rowDataB.split('-').join('-');
                return new Date(dateA) - new Date(dateB).getTime();
                break;
            case 'text':
                if (rowDataA < rowDataB) return -1;
                else if(rowDataA > rowDataB) return 1;
                return 0;
                break;
            case 'double':
                return rowDataA - rowDataB;
                break;
        }
    }
    let rows = [].slice.call(tbody.rows);
    rows.sort(compare);

    if (isSorted) rows.reverse();

    table.removeChild(tbody);
    for (let i = 0; i < rows.length; i++){
        tbody.appendChild(rows[i]);
    }
    table.appendChild(tbody);
}
table.addEventListener('click', (e) =>{
    const el = e.target;
    if (el.nodeName != 'TH') return;
    const index = el.cellIndex;
    const type = el.getAttribute('datatype');
    sortTable(index, type,colIndex == index);
    colIndex = (colIndex == index) ? -1 : index;
});