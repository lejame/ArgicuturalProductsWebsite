var dataLoaded = false;
$(document).ready(function () {
  if (dataLoaded === false) {
    loadData();
  }
});
function loadData() {
  $.ajax({
    method: "GET",
    url: "http://localhost:8080/blog",
    headers: {
      "Content-Type": "application/json",
      Authorization: "Bearer " + localStorage.getItem("Token"),
    },
  })
    .done(function (result) {
      displayBlogs(result.data);
      var data = JSON.stringify(result.data);
      localStorage.setItem("BlogList", data);
      dataLoaded = true; // Set dataLoaded to true after loading data
    })
    .fail(function (jqXHR, textStatus, errorThrown) {
      console.error("AJAX request failed: " + textStatus, errorThrown);
    });
}
function displayBlogs(blogs) {
  var htmlItem = "";
  for (var i = 0; i < blogs.length; i++) {
    var blog = blogs[i];
    htmlItem += createBlogHTML(blog);
  }
  $(".product_tab_content").empty().append(htmlItem);
}
function createBlogHTML(blog) {
  return `
            <tr>
                <td><img class="tbl-thumb" src="${blog.image} "
                        alt="blog Image"></td>
                <td>${blog.name}</td>
                <td>${blog.author}</td>
                <td><textarea name="" id="" cols="30" rows="3" cols="7">${
                  blog.content
                }</textarea></td>
                <td>${blog.date}</td>

                <td>
                    <div class="d-flex justify-content-center">
                        <button type="button"
                            class="btn btn-outline-success dropdown-toggle dropdown-toggle-split"
                            data-bs-toggle="dropdown" aria-haspopup="true"
                            aria-expanded="false" data-display="static">
                            <span class="sr-only"><i
                                    class="ri-settings-3-line"></i></span>
                        </button>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="#">Edit</a>
                            <a class="dropdown-item" href="#">Delete</a>
                        </div>
                    </div>
                </td>
            </tr>`;
}
