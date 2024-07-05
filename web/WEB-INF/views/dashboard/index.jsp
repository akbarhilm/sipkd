 
<script type="text/javascript" src="${pageContext.request.contextPath}/static/assets/vendors/jquery/dist/jquery.min.js"></script>
       
<div class="right_col" role="main">
    
    <!--<div class="">-->
        <div class="page-title">
            <div class="title_left">
                <h3></h3>
            </div>

            <div class="title_right">
                <div class="col-md-5 col-sm-5 col-xs-12 form-group pull-right top_search">
                    
                </div>
            </div>
        </div>
<section class="content">
        <div class="clearfix">
        <div class="row">
            <div class="col-md-4 text-center">
                <div class="box ">
                    <div class="box-header">
                        Saldo Akhir
                        <hr class='style-one'></hr>
                    </div>
                     <div class="box-body">
                         <h2><span id='sa'>Rp. </span></h2>
                     </div>
                </div>
            </div>
            <div class="col-md-4 text-center">
                <div class="box box-warning">
                    <div class="box-header">
                        Penerimaan
                    </div>
                    <div class="box-body ">
                         <hr class='style-one'>
                         <h2>
                             <span id='spn'>Rp. </span>
                         </h2>
                         </hr>
                         
                     </div>
                    
                </div>
            </div>
            <div class="col-md-4 text-center">
                <div class="box box-warning">
                    <div class="box-header">
                        Pengeluaran
                    </div>
                    <div class="box-body ">
                        <hr class='style-one'>
                         <h2>
                             <span id='spg'>Rp. </span>
                         </h2>
                         </hr>
                     </div>
                    
                </div>
            </div>
        </div>
      
        <div class='row'>
            <div class='col-md-12'>
                <div class='box-table'>
                    <div class='box-header'>
                        <h2>Penerimaan</h2>
                         <hr class='style-one'>
                    </div>
                    <div class='box-body'>
                         <table id="penerimaan" class="table center" cellspacing="0" width="98%">
                            <thead>
                            <th>No</th>   
                            <th>Rekening</th>
                            <th>Saldo</th>
                            </thead>
                        </table>
                    </div>
                </div>
            </div>
           
     </div>
     <div class='row'>
            <div class='col-md-12'>
                <div class='box-table'>
                    <div class='box-header'>
                        <h2>Pengeluaran</h2>
                        <hr class='style-one'>
                    </div>
                    <div class='box-body'>
                        <table id="pengeluaran" class="table center" cellspacing="0" width="98%">
                            <thead>
                            <th>No</th>
                            <th>Rekening</th>
                            <th>Saldo</th>
                            </thead>
                        </table>
                    </div>
                </div>
            </div>
           
     </div>
        </div>
</section>
    <!--</div>-->
</div>

   <script src="${pageContext.request.contextPath}/static/js/aplikasi/dashboard/dashboard.js" type="text/javascript"></script>
        
        